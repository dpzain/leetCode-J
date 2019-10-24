package leetCode.retrospective;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author dpzain
 * @Description 全排列
 * https://leetcode-cn.com/problems/permutations/
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 输入: [1,2,3]
    输出:
    [
    [1,2,3],
    [1,3,2],
    [2,1,3],
    [2,3,1],
    [3,1,2],
    [3,2,1]
    ]
 *
 * @Date Created in 16:47 2019/10/9
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums ={1,2,3};
        System.out.println(JSON.toJSONString(permute(nums)));
    }


    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res,nums,new ArrayList<>(),visited);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> temp, int[] visited) {
        if(temp.size()==nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i] ==1) continue;
            visited[i] = 1;
            temp.add(nums[i]);
            backtrack(res,nums,temp,visited);
            visited[i]=0;
            temp.remove(temp.size()-1);
        }
    }
}


