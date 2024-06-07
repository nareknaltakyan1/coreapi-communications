package com.nnaltakyan.core.communications.domain.sentemail.model;

import com.nnaltakyan.api.core.common.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "sentemail", indexes = { @Index(name = "idx_sentemail_toemail", columnList = "toEmail"),
	@Index(name = "idx_sentemail_fromemail", columnList = "fromemail"), @Index(name = "idx_sentemail_sent", columnList = "sent") })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SentEmail extends BaseEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 4000)
	@Column(nullable = false, length = 4000)
	private String data;

	@NotNull
	@Email
	@Column(nullable = false)
	private String fromEmail;

	@NotNull
	@Column(nullable = false)
	private String fromName;

	@NotNull
	@Email
	@Column(nullable = false)
	private String toEmail;

	@NotNull
	@Column(nullable = false)
	private String toName;

	@Column(nullable = true, length = 255)
	private String preferredLocale;

	@NotNull
	@Column(nullable = true)
	private Instant sent;
}