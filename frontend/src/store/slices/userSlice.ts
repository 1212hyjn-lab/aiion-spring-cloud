import { StateCreator } from 'zustand';
import { AppStore } from '../types';

export interface UserState {
  user: {
    id: string;
    name: string;
    email: string;
  } | null;
  isAuthenticated: boolean;
}

export interface UserActions {
  setUser: (user: UserState['user']) => void;
  setAuthenticated: (isAuthenticated: boolean) => void;
  logout: () => void;
}

export interface UserSlice extends UserState, UserActions {}

export const createUserSlice: StateCreator<
  AppStore,
  [],
  [],
  UserSlice
> = (set) => ({
  // 초기 상태
  user: null,
  isAuthenticated: false,

  // 액션
  setUser: (user) => set({ user }, false, 'setUser'),
  setAuthenticated: (isAuthenticated) =>
    set({ isAuthenticated }, false, 'setAuthenticated'),
  logout: () =>
    set({ user: null, isAuthenticated: false }, false, 'logout'),
});

