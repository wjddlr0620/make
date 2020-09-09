package coffee;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Cancelation_table")
public class Cancelation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long cancelId;
    private Long requestId;
    private String status;
    private String cancelTime;

    @PostPersist
    public void onPostPersist(){
        MakeCanceled makeCanceled = new MakeCanceled();
        BeanUtils.copyProperties(this, makeCanceled);
        makeCanceled.publishAfterCommit();

        coffee.external.StorageCancel storageCancel = new coffee.external.StorageCancel();
        // mappings goes here

        storageCancel.setRequestId(makeCanceled.getRequestId());
        storageCancel.setStorageStatus("Canceled");

        MakeApplication.applicationContext.getBean(coffee.external.StorageCancelService.class)
            .storageCancel(storageCancel);
    }


    public Long getCancelId() {
        return cancelId;
    }

    public void setCancelId(Long cancelId) {
        this.cancelId = cancelId;
    }
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }




}
