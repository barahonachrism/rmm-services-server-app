package com.ninjaone.rmm.domain.common;

import lombok.experimental.UtilityClass;

/**
 * Centralize parameters needs in business rules of transaction operations
 */
@UtilityClass
public class TransactionLimits {
    public final int QUOTA_TRANSACTIONS_LIMIT = 3;
    public final int QUOTA_SECONDS_TRANSACTIONS_LIMIT = 120;
}
