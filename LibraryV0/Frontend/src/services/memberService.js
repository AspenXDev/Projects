import axios from "axios";

const API_URL = "http://localhost:8081/members";

// Attach JWT token for authorization
const getAuthHeaders = () => {
  const token = localStorage.getItem("token");
  return { headers: { Authorization: `Bearer ${token}` } };
};

// Fetch members with optional search query
export const getMembers = async (search = "") => {
  try {
    const response = await axios.get(
      `${API_URL}?search=${encodeURIComponent(search)}`,
      getAuthHeaders()
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching members:", error);
    return [];
  }
};

// Standard CRUD operations
export const createMember = (member) =>
  axios.post(API_URL, member, getAuthHeaders());

export const getMemberById = (id) =>
  axios.get(`${API_URL}/${id}`, getAuthHeaders());

export const updateMember = (id, member) =>
  axios.put(`${API_URL}/${id}`, member, getAuthHeaders());

export const deleteMember = (id) =>
  axios.delete(`${API_URL}/${id}`, getAuthHeaders());
