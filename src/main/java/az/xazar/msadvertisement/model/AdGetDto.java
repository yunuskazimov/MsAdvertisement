package az.xazar.msadvertisement.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Advertisement GET Data")
public class AdGetDto {
    @ApiModelProperty("Unique id of Ad. Not used in post requests.")
    private Long id;

    @ApiModelProperty(value = "valid User ID of Ad.", example = "1")
    private Long userId;

    @ApiModelProperty(value = "Ad name. Used Only GET method", example = "Bayram")
    private String userName;

    @ApiModelProperty(value = "valid Ad name.", example = "Bayram")
    private String name;

    @ApiModelProperty(value = "valid description for Ad", example = "31 dekabr Milli Hemreylik gunudur")
    private String description;

    @ApiModelProperty(value = "Ad actual type, {share or pending}", example = "share")
    private StatusEnum status;

    @ApiModelProperty(value = "Create Date of Ad. Not used in POST requests.", example = "Only For Get POST: "
            + "[2021,12,21,14,45,36,777973000]")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "Update Date of Ad. Not used in POST requests.", example = "Only For Get POST "
            + "[2021,12,21,14,45,36,777973000]")
    private LocalDateTime updatedAt;
}
