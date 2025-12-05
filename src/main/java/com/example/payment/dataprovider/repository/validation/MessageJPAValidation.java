package com.example.payment.dataprovider.repository.validation;


import com.example.payment.dataprovider.repository.exception.TransactionException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.dao.UncategorizedDataAccessException;

public abstract class MessageJPAValidation {

    protected MessageJPAValidation() {
    }

    public void messageExceptionFactory(DataAccessException dataAccessException){

        switch (dataAccessException) {

            case OptimisticLockingFailureException e ->
                    throw new TransactionException(optimisticLockingFailureExceptionMessage(e), e);

            case PessimisticLockingFailureException e ->
                    throw new TransactionException(pessimisticLockingFailureExceptionMessage(e), e);

            case ConcurrencyFailureException e -> throw new TransactionException(concurrencyFailureExceptionMessage(e), e);

            case DataAccessResourceFailureException e ->
                    throw new TransactionException(dataAccessResourceFailureExceptionMessage(e), e);

            case DuplicateKeyException e -> throw new TransactionException(duplicateKeyExceptionMessage(e), e);

            case DataIntegrityViolationException e ->
                    throw new TransactionException(dataIntegrityViolationExceptionMessage(e), e);

            case EmptyResultDataAccessException e ->
                    throw new TransactionException(emptyResultDataAccessExceptionMessage(e), e);

            case IncorrectResultSizeDataAccessException e ->
                    throw new TransactionException(incorrectResultSizeDataAccessExceptionMessage(e), e);

            case DataRetrievalFailureException e ->
                    throw new TransactionException(dataRetrievalFailureExceptionMessage(e), e);

            case IncorrectUpdateSemanticsDataAccessException e ->
                    throw new TransactionException(incorrectUpdateSemanticsDataAccessExceptionMessage(e), e);

            case InvalidDataAccessApiUsageException e ->
                    throw new TransactionException(invalidDataAccessApiUsageExceptionMessage(e), e);

            case TypeMismatchDataAccessException e ->
                    throw new TransactionException(typeMismatchDataAccessExceptionMessage(e), e);

            case InvalidDataAccessResourceUsageException e ->
                    throw new TransactionException(invalidDataAccessResourceUsageExceptionMessage(e), e);

            case NonTransientDataAccessResourceException e ->
                    throw new TransactionException(nonTransientDataAccessResourceExceptionMessage(e), e);

            case PermissionDeniedDataAccessException e ->
                    throw new TransactionException(permissionDeniedDataAccessExceptionMessage(e), e);

            case UncategorizedDataAccessException e ->
                    throw new TransactionException(uncategorizedDataAccessExceptionMessage(e), e);

            case NonTransientDataAccessException e ->
                    throw new TransactionException(nonTransientDataAccessExceptionMessage(e), e);

            case QueryTimeoutException e -> throw new TransactionException(queryTimeoutExceptionMessage(e), e);

            case RecoverableDataAccessException e ->
                    throw new TransactionException(recoverableDataAccessExceptionMessage(e), e);

            case TransientDataAccessResourceException e ->
                    throw new TransactionException(transientDataAccessResourceExceptionMessage(e), e);

            case TransientDataAccessException e ->
                    throw new TransactionException(transientDataAccessExceptionMessage(e), e);

            default -> throw new IllegalStateException(
                    "Unexpected DataAccessException type: " + dataAccessException.getClass().getName(),
                    dataAccessException
            );
        }
    }

    protected String optimisticLockingFailureExceptionMessage(OptimisticLockingFailureException e) {
        return "The resource was modified by another transaction (optimistic locking failure)." + e;
    }

    protected String pessimisticLockingFailureExceptionMessage(PessimisticLockingFailureException e) {
        return "Failed to acquire a pessimistic lock on the resource." + e;
    }

    protected String concurrencyFailureExceptionMessage(ConcurrencyFailureException e) {
        return "A concurrency error occurred while accessing the database." + e;
    }

    protected String dataAccessResourceFailureExceptionMessage(DataAccessResourceFailureException e) {
        return "Failed to access the data resource." + e;
    }

    protected String duplicateKeyExceptionMessage(DuplicateKeyException e) {
        return "A resource with the same unique key already exists." + e;
    }

    protected String dataIntegrityViolationExceptionMessage(DataIntegrityViolationException e) {
        return "Operation violates data integrity constraints." + e;
    }

    protected String emptyResultDataAccessExceptionMessage(EmptyResultDataAccessException e) {
        return "No data found for the requested resource." + e;
    }

    protected String incorrectResultSizeDataAccessExceptionMessage(IncorrectResultSizeDataAccessException e) {
        return "Unexpected number of results returned from the database." + e;
    }

    protected String dataRetrievalFailureExceptionMessage(DataRetrievalFailureException e) {
        return "Failed to retrieve the requested data from the database." + e;
    }

    protected String incorrectUpdateSemanticsDataAccessExceptionMessage(IncorrectUpdateSemanticsDataAccessException e) {
        return "Unexpected number of rows updated in the database." + e;
    }

    protected String invalidDataAccessApiUsageExceptionMessage(InvalidDataAccessApiUsageException e) {
        return "Invalid usage of the data access API." + e;
    }

    protected String typeMismatchDataAccessExceptionMessage(TypeMismatchDataAccessException e) {
        return "A type mismatch occurred while mapping data from the database." + e;
    }

    protected String invalidDataAccessResourceUsageExceptionMessage(InvalidDataAccessResourceUsageException e) {
        return "Invalid usage of the data resource." + e;
    }

    protected String nonTransientDataAccessResourceExceptionMessage(NonTransientDataAccessResourceException e) {
        return "A non-transient data resource error occurred." + e;
    }

    protected String permissionDeniedDataAccessExceptionMessage(PermissionDeniedDataAccessException e) {
        return "You do not have permission to perform this data operation." + e;
    }

    protected String uncategorizedDataAccessExceptionMessage(UncategorizedDataAccessException e) {
        return "An uncategorized data access error occurred." + e;
    }

    protected String nonTransientDataAccessExceptionMessage(NonTransientDataAccessException e) {
        return "A non-transient data access error occurred." + e;
    }

    protected String queryTimeoutExceptionMessage(QueryTimeoutException e) {
        return "The database query timed out. Please try again later." + e;
    }

    protected String recoverableDataAccessExceptionMessage(RecoverableDataAccessException e) {
        return "A recoverable data access error occurred. Please try again." + e;
    }

    protected String transientDataAccessResourceExceptionMessage(TransientDataAccessResourceException e) {
        return "A transient data resource error occurred. Please try again later." + e;
    }

    protected String transientDataAccessExceptionMessage(TransientDataAccessException e) {
        return "A transient data access error occurred. Please try again." + e;
    }
}
