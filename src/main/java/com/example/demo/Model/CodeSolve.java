package com.example.demo.Model;

public class CodeSolve {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column codesolve.id
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column codesolve.title
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column codesolve.create_time
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column codesolve.view_count
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    private Long viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column codesolve.like_count
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    private Long likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column codesolve.tag
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column codesolve.solution
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    private String solution;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column codesolve.id
     *
     * @return the value of codesolve.id
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column codesolve.id
     *
     * @param id the value for codesolve.id
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column codesolve.title
     *
     * @return the value of codesolve.title
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column codesolve.title
     *
     * @param title the value for codesolve.title
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column codesolve.create_time
     *
     * @return the value of codesolve.create_time
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column codesolve.create_time
     *
     * @param createTime the value for codesolve.create_time
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column codesolve.view_count
     *
     * @return the value of codesolve.view_count
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public Long getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column codesolve.view_count
     *
     * @param viewCount the value for codesolve.view_count
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column codesolve.like_count
     *
     * @return the value of codesolve.like_count
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column codesolve.like_count
     *
     * @param likeCount the value for codesolve.like_count
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column codesolve.tag
     *
     * @return the value of codesolve.tag
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column codesolve.tag
     *
     * @param tag the value for codesolve.tag
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column codesolve.solution
     *
     * @return the value of codesolve.solution
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public String getSolution() {
        return solution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column codesolve.solution
     *
     * @param solution the value for codesolve.solution
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    public void setSolution(String solution) {
        this.solution = solution == null ? null : solution.trim();
    }
}