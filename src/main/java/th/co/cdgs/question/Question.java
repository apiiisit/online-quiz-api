package th.co.cdgs.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.cdgs.choice.Choice;
import th.co.cdgs.quiz.Quiz;


@Entity
@Table(name = "question")
public class Question {

	@Id
	@SequenceGenerator(name = "questionSequence", sequenceName = "question_id_seq",
	allocationSize = 1, initialValue = 15)
	@GeneratedValue(generator = "questionSequence")
	@Column(name = "question_id")
	private Integer questionId;

	@Column(name = "question_name")
	private String questionName;

	@Column(name = "question_type", length = 1)
	private String questionType;

	@Column(name = "question_time")
	private Integer questionTime;

	@Column(name = "verified")
	private Boolean verified;
	
	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	@OneToMany(
			fetch = FetchType.EAGER,
			mappedBy = "question",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<Choice> choiceArr;
	
	@Column(name = "choice_correct_length")
	private Integer choiceCorrectLength;

	public Question() {
		this.choiceArr = new ArrayList<>();
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public Integer getQuestionTime() {
		return questionTime;
	}

	public void setQuestionTime(Integer questionTime) {
		this.questionTime = questionTime;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
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

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<Choice> getChoiceArr() {
		return choiceArr;
	}

	public void setChoiceArr(List<Choice> choiceArr) {
		this.choiceArr = choiceArr;
	}

	public Integer getChoiceCorrectLength() {
		return choiceCorrectLength;
	}

	public void setChoiceCorrectLength(Integer choiceCorrectLength) {
		this.choiceCorrectLength = choiceCorrectLength;
	}

}
