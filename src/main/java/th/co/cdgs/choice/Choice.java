package th.co.cdgs.choice;

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

import th.co.cdgs.choice.correct.ChoiceCorrect;
import th.co.cdgs.question.Question;


@Entity
@Table(name = "choice")
public class Choice {

	@Id
    @SequenceGenerator(name = "choiceSequence", sequenceName = "choice_id_seq",
            allocationSize = 1, initialValue = 100)
    @GeneratedValue(generator = "choiceSequence")
    @Column(name = "choice_id")
	private Integer choiceId;
	
	@Column(name = "choice_name")
	private String choiceName;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "choice_correct_id")
	private ChoiceCorrect choiceCorrect;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "update_time")
	private Date updateTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
	private Question question;

	public Integer getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(Integer choiceId) {
		this.choiceId = choiceId;
	}

	public String getChoiceName() {
		return choiceName;
	}

	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}
	
	public ChoiceCorrect getChoiceCorrect() {
		return choiceCorrect;
	}

	public void setChoiceCorrect(ChoiceCorrect choiceCorrect) {
		this.choiceCorrect = choiceCorrect;
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

	public void setQuestion(Question question) {
		this.question = question;
	}

}
