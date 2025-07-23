package com.recruit.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.recruit.vo.Sample;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SampleRequestDto {

    @NotNull(message  = "id는 필수입니다.")
    @NotBlank(message = "id는 공백일 수 없습니다.")
    private String id;

    @NotNull(message  = "name은 필수입니다.")
    @NotBlank(message = "name은 공백일 수 없습니다.")
    private String name;

    public Sample toEntity() {
        return Sample.builder().id(id).name(name).build();
    }
}