// SPDX-License-Identifier: MIT
pragma solidity ^0.8.9;

library RaffleStructs {
    struct PayInfo {
        uint16 _officeServiceRatio; // The official commission ratio when NFT is sold, 3000 means 30%
        uint16 _ownerRewardRatio; // The official commission ratio when NFT is transferred to token, 3000 means 30%
        uint256 _swapDuration; // The size of the time window for users to choose to exchange ETH after the lottery
        address _officialAccount;
    }
}