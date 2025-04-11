package com.example.demo.Dto;

import java.time.LocalDateTime;
import com.example.demo.model.Club;

public class MatchDto {
	private Long id;
	private Integer roundNumber;
	private Club host;
	private Club guest;
	private LocalDateTime date;
}
