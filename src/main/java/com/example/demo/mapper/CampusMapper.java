package com.example.demo.mapper;

import com.example.demo.Model.Campus;
import com.example.demo.Model.CampusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CampusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    long countByExample(CampusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int deleteByExample(CampusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int insert(Campus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int insertSelective(Campus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    List<Campus> selectByExampleWithBLOBsWithRowbounds(CampusExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    List<Campus> selectByExampleWithBLOBs(CampusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    List<Campus> selectByExampleWithRowbounds(CampusExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    List<Campus> selectByExample(CampusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    Campus selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int updateByExampleSelective(@Param("record") Campus record, @Param("example") CampusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") Campus record, @Param("example") CampusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int updateByExample(@Param("record") Campus record, @Param("example") CampusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int updateByPrimaryKeySelective(Campus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(Campus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table campus
     *
     * @mbg.generated Thu May 14 13:57:28 CST 2020
     */
    int updateByPrimaryKey(Campus record);
}