package th.co.cdgs.counter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "counter")
public class Counter {
	@Id
    @SequenceGenerator(name = "counterSequence", sequenceName = "counter_id_seq",
            allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "counterSequence")
    @Column(name = "counter_id")
	private Integer counterId;
	
	@Column(name = "counter_category")
	private Integer counterCategory;
	
	@Column(name = "counter_category_active")
	private Integer counterCategoryActive;
	
	@Column(name = "counter_quiz")
	private Integer counterQuiz;
	
	@Column(name = "counter_quiz_active")
	private Integer counterQuizActive;
	
	@Column(name = "counter_question")
	private Integer counterQuestion;

	@Column(name = "counter_question_active")
	private Integer counterQuestionActive;
	
	@Column(name = "counter_user")
	private Integer counterUser;
	
	@Column(name = "counter_task")
	private Integer counterTask;
	
	@Column(name = "counter_task_status")
	private Integer counterTaskStatus;

	public Integer getCounterId() {
		return counterId;
	}

	public void setCounterId(Integer counterId) {
		this.counterId = counterId;
	}

	public Integer getCounterCategory() {
		return counterCategory;
	}

	public void setCounterCategory(Integer counterCategory) {
		this.counterCategory = counterCategory;
	}

	public Integer getCounterCategoryActive() {
		return counterCategoryActive;
	}

	public void setCounterCategoryActive(Integer counterCategoryActive) {
		this.counterCategoryActive = counterCategoryActive;
	}

	public Integer getCounterQuiz() {
		return counterQuiz;
	}

	public void setCounterQuiz(Integer counterQuiz) {
		this.counterQuiz = counterQuiz;
	}

	public Integer getCounterQuizActive() {
		return counterQuizActive;
	}

	public void setCounterQuizActive(Integer counterQuizActive) {
		this.counterQuizActive = counterQuizActive;
	}

	public Integer getCounterQuestion() {
		return counterQuestion;
	}

	public void setCounterQuestion(Integer counterQuestion) {
		this.counterQuestion = counterQuestion;
	}

	public Integer getCounterQuestionActive() {
		return counterQuestionActive;
	}

	public void setCounterQuestionActive(Integer counterQuestionActive) {
		this.counterQuestionActive = counterQuestionActive;
	}

	public Integer getCounterUser() {
		return counterUser;
	}

	public void setCounterUser(Integer counterUser) {
		this.counterUser = counterUser;
	}

	public Integer getCounterTask() {
		return counterTask;
	}

	public void setCounterTask(Integer counterTask) {
		this.counterTask = counterTask;
	}

	public Integer getCounterTaskStatus() {
		return counterTaskStatus;
	}

	public void setCounterTaskStatus(Integer counterTaskStatus) {
		this.counterTaskStatus = counterTaskStatus;
	}
	
	

}
