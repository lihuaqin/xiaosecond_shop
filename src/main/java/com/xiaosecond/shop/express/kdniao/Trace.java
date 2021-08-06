package com.xiaosecond.shop.express.kdniao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Trace {
    @JsonProperty("AcceptTime")
    private String acceptTime;
    @JsonProperty("AcceptStation")
    private String acceptStation;
}
