import React from 'react';
import { PathfinderView as PathfinderViewType } from '../types';

interface PathfinderViewProps {
  pathfinderView: PathfinderViewType;
  setPathfinderView: (view: PathfinderViewType) => void;
  darkMode?: boolean;
}

export const PathfinderView: React.FC<PathfinderViewProps> = ({
  pathfinderView,
  setPathfinderView,
  darkMode = false,
}) => {
  return (
    <div className={`flex-1 flex flex-col overflow-hidden ${darkMode ? 'bg-gray-900' : 'bg-[#e8e2d5]'}`}>
      <div className="flex-1 overflow-y-auto p-6">
        <div className="max-w-4xl mx-auto space-y-6">
          <h1 className="text-3xl font-bold text-gray-900 text-center">Path finder</h1>
          <p className="text-gray-600">Pathfinder view: {pathfinderView}</p>
        </div>
      </div>
    </div>
  );
};

