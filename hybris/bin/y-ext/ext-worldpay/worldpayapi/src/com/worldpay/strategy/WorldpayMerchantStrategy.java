package com.worldpay.strategy;

import com.worldpay.config.merchant.WorldpayMerchantConfigData;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;

/**
 * Exposes methods to retrieve the configured merchants.
 */
public interface WorldpayMerchantStrategy {

    String DESKTOP_MERCHANT = "web";
    String CUSTOMER_SERVICE_MERCHANT = "customerService";
    String REPLENISHMENT_MERCHANT = "replenishment";

    /**
     * Returns the merchant configured for the current uiExperience
     *
     * @param uiExperienceLevel
     * @return
     */
    WorldpayMerchantConfigData getMerchant(final UiExperienceLevel uiExperienceLevel);

    /**
     * Returns the merchant configured to be used when the checkout is done by a Customer Service Agent
     *
     * @return
     */
    WorldpayMerchantConfigData getCustomerServiceMerchant();

    /**
     * Returns the merchant configured for Replenishment
     *
     * @return
     */
    WorldpayMerchantConfigData getReplenishmentMerchant();
}
