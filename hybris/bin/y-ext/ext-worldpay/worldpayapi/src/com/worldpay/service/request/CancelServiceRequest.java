package com.worldpay.service.request;


import com.worldpay.config.WorldpayConfig;
import com.worldpay.service.WorldpayServiceGateway;
import com.worldpay.service.model.MerchantInfo;

/**
 * This class represents the details that must be passed to a call to {@link WorldpayServiceGateway#cancel(CancelServiceRequest) cancel()} in the
 * WorldpayServiceGateway
 * <p/>
 * <p>No further parameters are required on top of the standard ones</p>
 */
public class CancelServiceRequest extends AbstractServiceRequest {

    protected CancelServiceRequest(WorldpayConfig config, MerchantInfo merchantInfo, String orderCode) {
        super(config, merchantInfo, orderCode);
    }

    /**
     * Static convenience method for creating an instance of the CancelServiceRequest
     *
     * @param config    worldpayConfig to be used in the Worldpay call
     * @param merch     merchantInfo to be used in the Worldpay call
     * @param orderCode orderCode to be used in the Worldpay call
     * @return new instance of the CancelServiceRequest initialised with input parameters
     */
    public static CancelServiceRequest createCancelRequest(WorldpayConfig config, MerchantInfo merch, String orderCode) {
        if (config == null || merch == null || orderCode == null) {
            throw new IllegalArgumentException("WorldpayConfig, MerchantInfo and Order Code cannot be null");
        }
        return new CancelServiceRequest(config, merch, orderCode);
    }
}
