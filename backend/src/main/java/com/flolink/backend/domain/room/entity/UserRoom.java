package com.flolink.backend.domain.room.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.flolink.backend.domain.feed.entity.Feed;
import com.flolink.backend.domain.feed.entity.FeedComment;
import com.flolink.backend.domain.noti.entity.Noti;
import com.flolink.backend.domain.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "user_room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE user_room SET use_yn = false WHERE user_room_id = ?")
@SQLRestriction("use_yn = true")
public class UserRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_room_id", nullable = false, length = 100)
	private Integer userRoomId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRoom", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Noti> notiList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRoom", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Nickname> nickNameList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRoom", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Feed> feedList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRoom", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FeedComment> feedCommentList;

	@Column(name = "create_at", nullable = false)
	private LocalDateTime createAt;

	@Column(name = "last_login_time")
	private LocalDateTime lastLoginTime;

	@Column(name = "use_yn", nullable = false)
	private Boolean useYn;

	@Column(name = "role", nullable = false, length = 45)
	private String role;

	public static UserRoom of(User user, Room room) {
		return UserRoom.builder()
			.user(user)
			.room(room)
			.createAt(LocalDateTime.now())
			.useYn(true)
			.role("member")
			.build();
	}

	public void updateLoginTime() {
		lastLoginTime = LocalDateTime.now();
	}

}
