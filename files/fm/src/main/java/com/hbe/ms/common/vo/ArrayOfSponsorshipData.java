package com.hbe.ms.common.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Class representing sponsorship data.")
public class ArrayOfSponsorshipData {

	private List<SponsorshipData> sponsorshipData;

	public List<SponsorshipData> getSponsorshipData() {
		return sponsorshipData;
	}

	public void setSponsorshipData(List<SponsorshipData> sponsorshipData) {
		this.sponsorshipData = sponsorshipData;
	}
}
