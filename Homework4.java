// package homework4;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author vitvalecka
 */
public class Homework4 extends MessageVisitor {
    
    public Homework4(Peer peer) {
        super(peer);
    }
    
    @Override
    boolean visitHaveMessage(HaveMessage message) {
        peer.peers2BlocksMap.get(message.sender)[message.blockIndex] = true;
        return false;
    }
    
    @Override
    boolean visitRequestMessage(RequestMessage message) {
        if (peer.data[message.blockIndex] != null) {
            message.sender.piece(peer, message.blockIndex, peer.data[message.blockIndex]);
        }
        return false;
    }
    
    @Override
    boolean visitPieceMessage(PieceMessage message) {
        peer.data[message.blockIndex] = message.data;
        peer.downloadedBlocksCount++;
        Set remote = peer.peers2BlocksMap.keySet();
        Iterator<PeerInterface> i = remote.iterator();
        
        while (i.hasNext()) {
            i.next().have(peer, message.blockIndex);
        }
        
        if (peer.downloadedBlocksCount == peer.totalBlocksCount) {
            return true;
        }
        
        return false;
    }
    
    @Override
    boolean visitIdleMessage(IdleMessage message) {
        Set remote = peer.peers2BlocksMap.keySet();
        Iterator<PeerInterface> i = remote.iterator();
        int[] rareness = new int[peer.totalBlocksCount];
        boolean[] block;
        
        while (i.hasNext()) {
            block = peer.peers2BlocksMap.get(i.next());
            
            for (int j = 0; j < block.length; j++) {
                if (block[j]) {
                    rareness[j]++;
                }
            }
        }
        
        // první pozice index, druhá pozice četnost
        int[] rarest = {0, rareness[0]};
        
        for (int j = 1; j < rareness.length; j++) {
            if (rareness[j] < rarest[1]) {
                rarest[0] = j;
                rarest[1] = rareness[j];
            }
        }
        
        i = remote.iterator();
        
        while (i.hasNext()) {
            PeerInterface foo = i.next();
            
            if (peer.peers2BlocksMap.get(foo)[rarest[0]]) {
                foo.request(peer, rarest[0]);
                break;
            }
        }
        
        return false;
    }    
}
