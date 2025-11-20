import { StateCreator } from 'zustand';
import { AppStore } from '../types';

export interface Player {
  id: string;
  name: string;
  position: string;
  team: string;
}

export interface Schedule {
  id: string;
  date: string;
  homeTeam: string;
  awayTeam: string;
  stadium: string;
}

export interface Team {
  id: string;
  name: string;
  league: string;
}

export interface Stadium {
  id: string;
  name: string;
  location: string;
  capacity: number;
}

export interface SoccerState {
  players: Player[];
  schedules: Schedule[];
  teams: Team[];
  stadiums: Stadium[];
}

export interface SoccerActions {
  setPlayers: (players: Player[]) => void;
  setSchedules: (schedules: Schedule[]) => void;
  setTeams: (teams: Team[]) => void;
  setStadiums: (stadiums: Stadium[]) => void;
  addPlayer: (player: Player) => void;
  addSchedule: (schedule: Schedule) => void;
  clearSoccerData: () => void;
}

export interface SoccerSlice extends SoccerState, SoccerActions {}

export const createSoccerSlice: StateCreator<
  AppStore,
  [],
  [],
  SoccerSlice
> = (set) => ({
  // 초기 상태
  players: [],
  schedules: [],
  teams: [],
  stadiums: [],

  // 액션
  setPlayers: (players) => set({ players }, false, 'setPlayers'),
  setSchedules: (schedules) => set({ schedules }, false, 'setSchedules'),
  setTeams: (teams) => set({ teams }, false, 'setTeams'),
  setStadiums: (stadiums) => set({ stadiums }, false, 'setStadiums'),
  addPlayer: (player) =>
    set(
      (state) => ({ players: [...state.players, player] }),
      false,
      'addPlayer'
    ),
  addSchedule: (schedule) =>
    set(
      (state) => ({ schedules: [...state.schedules, schedule] }),
      false,
      'addSchedule'
    ),
  clearSoccerData: () =>
    set(
      { players: [], schedules: [], teams: [], stadiums: [] },
      false,
      'clearSoccerData'
    ),
});

