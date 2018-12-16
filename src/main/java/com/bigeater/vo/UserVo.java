package com.bigeater.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:J on 2018/12/15.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String username;

    private String email;
}
