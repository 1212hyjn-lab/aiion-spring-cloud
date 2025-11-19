/**
 * 서비스 설정 파일
 * 
 * 12개 서비스 (AI 에이전트 5개 + MS 7개)를 위한 설정
 */

// TODO: AI 에이전트 설정 (5개)
export const AGENT_CONFIG = {
  // agent1: { ... },
  // agent2: { ... },
  // agent3: { ... },
  // agent4: { ... },
  // agent5: { ... },
} as const;

// TODO: 마이크로서비스 설정 (7개)
export const SERVICE_CONFIG = {
  // service1: { ... },
  // service2: { ... },
  // service3: { ... },
  // service4: { ... },
  // service5: { ... },
  // service6: { ... },
  // service7: { ... },
} as const;

// Gateway 설정
export const GATEWAY_CONFIG = {
  HOST: process.env.GATEWAY_HOST || 'gateway-server',
  PORT: process.env.GATEWAY_PORT || '8080',
  BASE_URL: `http://${process.env.GATEWAY_HOST || 'gateway-server'}:${process.env.GATEWAY_PORT || '8080'}`,
} as const;

