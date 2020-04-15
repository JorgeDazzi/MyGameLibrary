package br.dazzi.gamelibrary.controller.response;

import br.dazzi.gamelibrary.domain.entity.About;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AboutResponse {

    @JsonProperty(value = "version_num")
    @Getter
    private String versionNum;

    @JsonProperty(value = "version_name")
    @Getter
    private String versionName;

    public AboutResponse(About about) {
        this.setVersionName(about.getVersionName());
        this.setVersionNum(about.getVersionNum());
    }
}
