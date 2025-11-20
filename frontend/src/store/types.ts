/**
 * 전체 스토어 타입 정의
 * 
 * 슬라이스 패턴을 사용한 확장 가능한 타입 구조
 * 
 * 구조:
 * - 각 도메인별로 독립적인 슬라이스
 * - 타입 안정성 보장
 */

import { UserSlice } from './slices/userSlice';
import { SoccerSlice } from './slices/soccerSlice';
import { UISlice } from './slices/uiSlice';

// 공통 설정 타입
export interface AppConfig {
  // 공통 설정 추가 가능
}

// 전체 스토어 타입 (모든 슬라이스 통합)
export interface AppStore
  extends AppConfig,
    UserSlice,
    SoccerSlice,
    UISlice {
  // 공통 액션
  resetStore: () => void;
}
