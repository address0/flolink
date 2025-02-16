package com.flolink.backend.global.auth.dto.response.OAuth;

import java.math.BigDecimal;

import com.flolink.backend.domain.myroom.entity.MyRoom;
import com.flolink.backend.domain.user.entity.User;
import com.flolink.backend.domain.user.entity.enumtype.EmotionType;
import com.flolink.backend.domain.user.entity.enumtype.ProfileType;
import com.flolink.backend.domain.user.entity.enumtype.RoleType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	private int userId;
	private MyRoom myRoom;
	private String username;
	private String nickname;
	private BigDecimal point;
	private RoleType role;
	private ProfileType profile;
	private EmotionType emotion;
	private boolean useYn;
	private String loginId;

	public static UserDTO from(User user) {
		return UserDTO.builder()
			.userId(user.getUserId())
			.myRoom(user.getMyRoom())
			.username(user.getUserName())
			.nickname(user.getNickname())
			.point(user.getPoint())
			.role(user.getRole())
			.profile(user.getProfile())
			.emotion(user.getEmotion())
			.useYn(user.isUseYn())
			.loginId(user.getLoginId())
			.build();
	}

}
