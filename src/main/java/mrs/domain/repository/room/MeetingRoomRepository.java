package mrs.domain.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.domain.model.MeetingRoom;

/**
 * リポジトリインタフェース
 *
 */
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {
	
	
}

