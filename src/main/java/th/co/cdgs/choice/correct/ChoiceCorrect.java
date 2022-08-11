package th.co.cdgs.choice.correct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.cdgs.choice.Choice;

@Entity
@Table(name = "choice_correct")
public class ChoiceCorrect {

	@Id
    @SequenceGenerator(name = "choiceCorrectSequence", sequenceName = "choice_correct_id_seq",
            allocationSize = 1, initialValue = 100)
    @GeneratedValue(generator = "choiceCorrectSequence")
    @Column(name = "choice_correct_id")
	private Integer choiceCorrectId;
	
	@Column(name = "choice_correct_check")
	private Boolean choiceCorrectCheck;
	
	@OneToOne()
    @JoinColumn(name = "choice_id")
	private Choice choice;

	public Integer getChoiceCorrectId() {
		return choiceCorrectId;
	}

	public void setChoiceCorrectId(Integer choiceCorrectId) {
		this.choiceCorrectId = choiceCorrectId;
	}

	public Boolean getChoiceCorrectCheck() {
		return choiceCorrectCheck;
	}

	public void setChoiceCorrectCheck(Boolean choiceCorrectCheck) {
		this.choiceCorrectCheck = choiceCorrectCheck;
	}

	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}
	
	
}
