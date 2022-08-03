package th.co.cdgs.category;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "category")
public class Category {
	
	@Id
    @SequenceGenerator(name = "categorySequence", sequenceName = "category_id_seq",
            allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "categorySequence")
    @Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "category_name", length = 100)
	private String categoryName;
	
	@Column(name = "category_description", length = 200)
	private String categoryDescription;
	
	@Column(name = "category_active")
	private Boolean categoryActive;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "update_time")
	private Date updateTime;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Boolean getCategoryActive() {
		return categoryActive;
	}

	public void setCategoryActive(Boolean categoryActive) {
		this.categoryActive = categoryActive;
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
}
