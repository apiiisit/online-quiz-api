package th.co.cdgs.task;

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

import th.co.cdgs.quiz.Quiz;
import th.co.cdgs.user.User;

@Entity
@Table(name = "task")
public class Task {

	@Id
    @SequenceGenerator(name = "taskSequence", sequenceName = "task_id_seq",
            allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "taskSequence")
    @Column(name = "task_id")
	private Integer taskId;
	
	@Column(name = "task_status")
	private Boolean taskStatus;
	
	@Column(name = "task_score")
	private Float taskScore;
	
	@Column(name = "task_pass")
	private String taskPass;
	
	@Column(name = "task_start")
	private Date taskStart;
	
	@Column(name = "task_finish")
	private Date taskFinish;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
	private Quiz quiz;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Boolean getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Boolean taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Float getTaskScore() {
		return taskScore;
	}

	public void setTaskScore(Float taskScore) {
		this.taskScore = taskScore;
	}

	public String getTaskPass() {
		return taskPass;
	}

	public void setTaskPass(String taskPass) {
		this.taskPass = taskPass;
	}

	public Date getTaskStart() {
		return taskStart;
	}

	public void setTaskStart(Date taskStart) {
		this.taskStart = taskStart;
	}

	public Date getTaskFinish() {
		return taskFinish;
	}

	public void setTaskFinish(Date taskFinish) {
		this.taskFinish = taskFinish;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
}
