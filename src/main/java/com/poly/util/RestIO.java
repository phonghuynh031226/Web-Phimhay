package com.poly.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RestIO {

    private static final ObjectMapper mapper = new ObjectMapper();

    // ========== Đọc  ==========
    public static String readJson(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        return sb.toString();
    }

    // ========== Ghi  ==========
    public static void writeJson(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }

    // ========== Đọc  ==========
    public static <T> T readObject(HttpServletRequest req, Class<T> clazz) throws IOException {
        String json = readJson(req);
        return mapper.readValue(json, clazz);
    }

    // ========== Ghi  ==========
    public static void writeObject(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String json = mapper.writeValueAsString(data);

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }

    // ========== Ghi  ==========
    public static void writeEmptyObject(HttpServletResponse resp) throws IOException {
        writeJson(resp, "{}");
    }
}
