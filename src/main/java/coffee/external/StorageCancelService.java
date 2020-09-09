
package coffee.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

//@FeignClient(name="storage", url="http://storage:8080")
@FeignClient(name="storage", url="${api.url.storage}")
public interface StorageCancelService {

    @RequestMapping(method= RequestMethod.GET, path="/storageCancels")
    public void storageCancel(@RequestBody StorageCancel storageCancel);

}