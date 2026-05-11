package com.example.demo.admin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdminLogDto {
    Long log_id;
    String url;
    String method;
    Long elapsed_ms;
    String request_body;
    int response_code;
}
