package org.bigdata.res;

import org.apache.commons.lang3.StringUtils;
import org.bigdata.domain.FileMsg;
import org.bigdata.domain.SimpleMsg;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.UUID;

@Path("file")
public class FileRes {
    //文件存储路径
    private static final String FILE_PATH = "D:/file/upload/";

    //文件上传
    @Path("upload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(@FormDataParam("file") InputStream inputStream,
                           @FormDataParam("file") FormDataContentDisposition disposition) {
        String fileName = UUID.randomUUID().toString();
        String originName;
        originName = disposition.getFileName();
        if (originName.contains("."))
            fileName = fileName +
                    originName.substring(originName.lastIndexOf("."), originName.length());
        try {
            writeToFileServer(inputStream, fileName);
            return Response.ok(new FileMsg(fileName)).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleMsg(e.getMessage()))
                    .build();
        }
    }

    private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {
        OutputStream outputStream = null;
        File file = new File(FILE_PATH);
        if (!file.exists())
            file.mkdirs();
        String qualifiedUploadFilePath = FILE_PATH + fileName;
        try {
            outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (outputStream != null) outputStream.close();
        }
        return qualifiedUploadFilePath;
    }

    @Path("download/{name}")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("name") String name) {
        String fileName = FILE_PATH + name;
        File file = new File(fileName);
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new SimpleMsg("file not found"))
                    .build();
        }

        //为图片格式提供浏览模式
        //文件扩展名
        String ext = name.substring(name.lastIndexOf(".") + 1, name.length());
        if (StringUtils.equalsAnyIgnoreCase(ext, new String[]{"jpg", "jpeg", "png", "bmp", "gif"})) {
            return Response.ok(file).header("Content-Type", "image/png").build();
        }
        return Response.ok(file)
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .build();
    }
}
