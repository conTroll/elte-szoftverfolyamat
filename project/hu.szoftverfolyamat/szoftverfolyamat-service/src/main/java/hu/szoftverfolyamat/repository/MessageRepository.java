package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT me FROM MessageEntity me LEFT JOIN FETCH me.userFrom uf LEFT JOIN FETCH me.userTo ut WHERE ((uf.credentialId = :viewParty AND ut.credentialId = :otherParty) OR (uf.credentialId = :otherParty AND ut.credentialId = :viewParty)) AND me.status = 'active' ORDER BY me.createdAt DESC")
    public List<MessageEntity> findForUserPair(@Param("viewParty") long viewPartyId, @Param("otherParty") long otherPartyId);
}
