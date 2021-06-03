package kodlamaio.hrms.entities.concretes;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resumes")
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "cover_letter")
	@NotBlank
	@NotNull
	private String coverLetter;

	@Column(name = "url")
	private String url;

	@Column(name = "linkedin_address")
	@NotBlank
	@NotNull
	private String linkedinAddress;

	@Column(name = "github_address")
	@NotBlank
	@NotNull
	private String githubAddress;

	@OneToOne()
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@OneToMany(mappedBy = "resume")
	private List<School> schools;

	@OneToMany(mappedBy = "resume")
	private List<JobExperience> jobExperiences;

	@OneToMany(mappedBy = "resume")
	private List<Language> languages;

	@OneToMany(mappedBy = "resume")
	private List<Technologie> technologies;

}
