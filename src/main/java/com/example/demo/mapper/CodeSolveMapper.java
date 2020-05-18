package com.example.demo.mapper;

import com.example.demo.Model.CodeSolve;
import com.example.demo.Model.CodeSolveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CodeSolveMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    long countByExample(CodeSolveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int deleteByExample(CodeSolveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int insert(CodeSolve record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int insertSelective(CodeSolve record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    List<CodeSolve> selectByExampleWithBLOBsWithRowbounds(CodeSolveExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    List<CodeSolve> selectByExampleWithBLOBs(CodeSolveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    List<CodeSolve> selectByExampleWithRowbounds(CodeSolveExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    List<CodeSolve> selectByExample(CodeSolveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    CodeSolve selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int updateByExampleSelective(@Param("record") CodeSolve record, @Param("example") CodeSolveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") CodeSolve record, @Param("example") CodeSolveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int updateByExample(@Param("record") CodeSolve record, @Param("example") CodeSolveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int updateByPrimaryKeySelective(CodeSolve record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(CodeSolve record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table codesolve
     *
     * @mbg.generated Mon May 18 11:28:49 CST 2020
     */
    int updateByPrimaryKey(CodeSolve record);
}