package net.zcscloud.zhuohcun.zeco.common.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@Entity
public abstract class LogicEntity{
    @Id
    @Column(insertable = false)
    protected int id;  //the id of the record

    @Column(insertable = false)
    protected int isdeleted;  //is the record deleted

    @Column(insertable = false)
    protected int deletedby;  //who deleted the record

    @Column(insertable = false)
    protected int insertedby;  //who inserted the record

    public int getIsdeleted() {
        return isdeleted;
    }
    public int getDeletedby() {
        return deletedby;
    }
    public int getInsertedby() {
        return insertedby;
    }
    public int getId() {
        return id;
    }
}
