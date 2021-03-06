package cn.yangliuqing.todolistbackend.repository;

import cn.yangliuqing.todolistbackend.pojo.entity.Remind;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/** @author yang */
@Repository
@Table("remind")
public interface RemindRepository extends CrudRepository<Remind, Integer> {
    /**
     * 返回比指定提醒时间更前的提醒任务
     *
     * @param remindTime 时间
     * @param completeFlag 事件是否被提醒过
     * @return 所有任务
     */
    List<Remind> findAllByRemindTimeBeforeAndCompleteFlag(
            LocalDateTime remindTime, Boolean completeFlag);

    /**
     * 通过userId查询所有的提醒
     *
     * @param userId 用户id
     * @return 该用户的所有提醒
     */
    List<Remind> findAllByUserId(Integer userId);

    /**
     * 通过事件的id更新已经提醒的标志
     *
     * @param completeFlag 事件更新的标志
     * @param remindId 事件的id
     */
    @Modifying
    @Transactional
    @Query(value = "update remind set complete_flag = ?1 where remind_id = ?2", nativeQuery = true)
    void updateCompleteFlagByRemindId(Boolean completeFlag, Integer remindId);
}
