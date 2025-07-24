package com.recruit.dto.menu;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.recruit.vo.menu.Menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MenuListRequestDto {

    @NotBlank(message = "ID는 필수입니다.")
    @Size(min = 1, max = 20, message = "ID는 1자 이상 20자 이하로 입력해주세요.")
    private String id;

    //@NotBlank(message = "메뉴 이름은 필수입니다.")
    //@Size(max = 50, message = "메뉴 이름은 50자 이내로 입력해주세요.")
    private String name;

    //@NotBlank(message = "URL은 필수입니다.")
    //@Pattern(regexp = "^/[a-zA-Z0-9/_\\-]*$", message = "URL은 슬래시(/)로 시작하고, 영문자, 숫자, 언더스코어(_)만 포함할 수 있습니다.")
    private String url;

    public Menu toEntity() {
        return Menu.builder()
                   .id(this.id)
                   .name(this.name)
                   .url(this.url)
                   .build();
    }
}
