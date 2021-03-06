package com.worldpay.service.response.transform;

import com.worldpay.exception.WorldpayModelTransformationException;
import com.worldpay.internal.model.PaymentService;
import com.worldpay.internal.model.Reply;
import com.worldpay.internal.model.Token;
import com.worldpay.service.response.CreateTokenResponse;
import com.worldpay.service.response.ServiceResponse;

public class CreateTokenResponseTransformer extends AbstractServiceResponseTransformer {

    @Override
    public ServiceResponse transform(final PaymentService paymentService) throws WorldpayModelTransformationException {
        final CreateTokenResponse createTokenResponse = new CreateTokenResponse();

        Object responseType = paymentService.getSubmitOrModifyOrInquiryOrReplyOrNotifyOrVerify().get(0);
        if (responseType == null) {
            throw new WorldpayModelTransformationException("No reply message in Worldpay create token response");
        }

        if (!(responseType instanceof Reply)) {
            throw new WorldpayModelTransformationException("Reply type from Worldpay not the expected type");
        }

        final Reply intReply = (Reply) responseType;
        final ServiceResponseTransformerHelper responseTransformerHelper = ServiceResponseTransformerHelper.getInstance();

        final Object response = intReply.getOrderStatusOrBatchStatusOrErrorOrAddressCheckResponseOrRefundableAmountOrAccountBatchOrShopperOrOkOrFuturePayAgreementStatusOrShopperAuthenticationResultOrFuturePayPaymentResultOrPricePointOrPaymentOptionOrToken().get(0);

        if (responseTransformerHelper.checkForError(createTokenResponse, intReply)) {
            return createTokenResponse;
        } else if (response instanceof Token) {
            createTokenResponse.setToken(responseTransformerHelper.buildTokenReply((Token) response));
        }

        return createTokenResponse;
    }
}
