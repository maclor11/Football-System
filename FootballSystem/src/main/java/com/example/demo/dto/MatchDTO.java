package com.example.demo.dto;

import java.time.LocalDateTime;
import com.example.demo.model.Club;

public class MatchDTO {
	private Long id;
	private Integer roundNumber;
	private Club host;
	private Club guest;
	private LocalDateTime date;
}
