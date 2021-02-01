package com.wenlincheng.pika.upms.entity.form.gateway;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredicateDefinition {
    private String name;
    private Map<String, String> args = new LinkedHashMap<>();
}
