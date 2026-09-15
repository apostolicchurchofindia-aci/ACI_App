package com.aci.core.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005\u00a8\u0006\u0006"}, d2 = {"toDomain", "Lcom/aci/core/domain/model/User;", "Lcom/aci/core/data/db/UserEntity;", "toEntity", "pinHash", "", "core-data_debug"})
public final class UserMappersKt {
    
    /**
     * [pinHash] is passed separately because credentials deliberately don't live on the domain model.
     */
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.core.data.db.UserEntity toEntity(@org.jetbrains.annotations.NotNull()
    com.aci.core.domain.model.User $this$toEntity, @org.jetbrains.annotations.NotNull()
    java.lang.String pinHash) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.core.domain.model.User toDomain(@org.jetbrains.annotations.NotNull()
    com.aci.core.data.db.UserEntity $this$toDomain) {
        return null;
    }
}