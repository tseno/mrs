package mrs.domain.model;

import java.io.Serializable;

import javax.persistence.*;

public class MeetingRoom implements Serializable {
	
	/**
	 * ID
	 * 主キー、自動採番
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomId;
	
	/**
	 * 部屋名
	 */
	private String roomName;

	
	
	
	
	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	

	
	
	
}
