// src/services/MemberService.js
import axios from "axios";

const API_URL = "http://localhost:8081/members";
const getAuthHeaders = () => ({
  headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
});

export const getMembers = async (search = "") => {
  try {
    return (
      await axios.get(
        `${API_URL}?search=${encodeURIComponent(search)}`,
        getAuthHeaders()
      )
    ).data;
  } catch (err) {
    console.error("Error fetching members:", err);
    return [];
  }
};

export const createMember = async (member) =>
  (await axios.post(API_URL, member, getAuthHeaders())).data;
export const getMemberById = async (id) =>
  (await axios.get(`${API_URL}/${id}`, getAuthHeaders())).data;
export const updateMember = async (id, member) =>
  (await axios.put(`${API_URL}/${id}`, member, getAuthHeaders())).data;
export const deleteMember = async (id) =>
  (await axios.delete(`${API_URL}/${id}`, getAuthHeaders())).data;
