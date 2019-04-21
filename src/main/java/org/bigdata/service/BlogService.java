package org.bigdata.service;

import org.bigdata.domain.Blog;

public class BlogService {
    public static Blog getById(String id) {
        return new Blog(id,"message");
    }
}
