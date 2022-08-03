package th.co.cdgs.quiz;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.cdgs.category.Category;

@Entity
@Table(name = "quiz")
public class Quiz {

	@Id
    @SequenceGenerator(name = "quizSequence", sequenceName = "quiz_id_seq",
            allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "quizSequence")
    @Column(name = "quiz_id")
	private Integer quizId;
	
	@Column(name = "quiz_name", length = 100)
	private String quizName;
	
	@Column(name = "quiz_description", length = 200)
	private String quizDescription;
	
	@Column(name = "quiz_pass")
	private Float quizPass;
	
	@Column(name = "number_of_question")
	private Integer numberOfQuestion;
	
	@Column(name = "quiz_start")
	private Date quizStart;
	
	@Column(name = "quiz_end")
	private Date quizEnd;
	
	@Column(name = "quiz_active")
	private Boolean quizActive;
	
	@Column(name = "quiz_password")
	private String quizPassword;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "update_time")
	private Date updateTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
	private Category category;

	public Integer getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getQuizDescription() {
		return quizDescription;
	}

	public void setQuizDescription(String quizDescription) {
		this.quizDescription = quizDescription;
	}

	public Float getQuizPass() {
		return quizPass;
	}

	public void setQuizPass(Float quizPass) {
		this.quizPass = quizPass;
	}

	public Integer getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(Integer numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}

	public Date getQuizStart() {
		return quizStart;
	}

	public void setQuizStart(Date quizStart) {
		this.quizStart = quizStart;
	}

	public Date getQuizEnd() {
		return quizEnd;
	}

	public void setQuizEnd(Date quizEnd) {
		this.quizEnd = quizEnd;
	}

	public Boolean getQuizActive() {
		return quizActive;
	}

	public void setQuizActive(Boolean quizActive) {
		this.quizActive = quizActive;
	}

	public String getQuizPassword() {
		return quizPassword;
	}

	public void setQuizPassword(String quizPassword) {
		this.quizPassword = quizPassword;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
