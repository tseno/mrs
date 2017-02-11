package mrs.domain.service.reservation;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mrs.domain.model.ReservableRoom;
import mrs.domain.model.ReservableRoomId;
import mrs.domain.model.Reservation;
import mrs.domain.model.RoleName;
import mrs.domain.model.User;
import mrs.domain.repository.reservation.ReservationRepository;
import mrs.domain.repository.room.ReservableRoomRepository;

@Service
@Transactional
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	ReservableRoomRepository reservableRoomRepository;
	
	/**
	 * 予約の一覧を返却する。
	 * 
	 * @param reservableRoomId
	 * @return
	 */
	public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
		
		return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
		
	}
	
	/**
	 * 予約する
	 * 
	 * @param reservation
	 * @return
	 */
	public Reservation reserve(Reservation reservation) {
		
		//予約から、部屋と日付を取得
		ReservableRoomId reservableRoomId = reservation.getReservableRoom().getReservableRoomId();
		
		//部屋と日付から、予約可能かどうか取得
		ReservableRoom reservable = reservableRoomRepository.findOne(reservableRoomId);
		
		if (reservable == null) {
			//予約可能ではないので、例外をスローする
			throw new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。");
		}
		
		//予約が重複していないか？
		boolean overlap = reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId)
				.stream()
				.anyMatch(x -> x.overlap(reservation));
		
		if (overlap) {
			//例外
			throw new AlreadyReservedException("入力の時間帯はすでに予約済みです。");
		}
		
		//保存
		reservationRepository.save(reservation);
				
		return reservation;
		
	}
	
	@PreAuthorize("hasRole('ADMIN') or #reservation.user.userId == principal.user.userId")
	public void cancel(@P("reservation") Reservation reservation) {
		
		reservationRepository.delete(reservation);
		
	}
	
	public Reservation findOne(Integer reservationId) {
		return reservationRepository.findOne(reservationId);
	}
	

}
