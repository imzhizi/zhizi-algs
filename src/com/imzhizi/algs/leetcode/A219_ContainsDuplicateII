# @param {Integer[]} nums
# @param {Integer} k
# @return {Boolean}
# created by zhizi on 9/22/2018
def contains_nearby_duplicate(nums, k)
  map = {}
  nums.each_with_index do |num, index|
    return true if map[num] && (map[num] - index).abs <= k
    map[num] = index
  end
  return false
end

