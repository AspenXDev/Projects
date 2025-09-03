import { api } from "./api.js";

// Get logged-in member's own info
export const getMyMemberInfo = async () => {
  const response = await api.get("/members/my");
  return response.data;
};

// Get all members
export const getAllMembers = async () => {
  const response = await api.get("/members");
  return response.data;
};
