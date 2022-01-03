package az.xazar.msadvertisement.model.Ad;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Advertisement Data")
public class AdDto {
    @ApiModelProperty("Unique id of Ad. Not used in post requests.")
    private Long id;

    @ApiModelProperty(value = "Enter valid User ID of Ad.", example = "1")
    private Long userId;

    @ApiModelProperty(value = "Enter valid Ad name.",example = "Bayram")
    private String name;

    @ApiModelProperty(value = "Enter valid description for Ad",example = "31 dekabr Milli Hemreylik gunudur")
    private String description;

    @ApiModelProperty(value = "Ad actual type",example = "share")
    private AdTypeEnum adType;

    @ApiModelProperty(value = "Ad deletion status",example = "false")
    private boolean isDeleted;

    @ApiModelProperty(value = "Enter Ad Status",example = "active")
    private AdStatusEnum status;

    @ApiModelProperty(value = "Create Date of Ad. Not used in POST requests." ,example = "Only For Get POST: "
           + "[2021,12,21,14,45,36,777973000]")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "Update Date of Ad. Not used in POST requests.",example = "Only For Get POST "
            + "[2021,12,21,14,45,36,777973000]")
    private LocalDateTime updatedAt;
}
