<?php

namespace App\Repository;

use App\Entity\Bid;
use App\Entity\Produit;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\Response;
use Doctrine\DBAL\Connection;
use phpDocumentor\Reflection\Types\Boolean;

/**
 * @extends ServiceEntityRepository<Bid>
 *
 * @method Bid|null find($id, $lockMode = null, $lockVersion = null)
 * @method Bid|null findOneBy(array $criteria, array $orderBy = null)
 * @method Bid[]    findAll()
 * @method Bid[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class BidRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Bid::class);
    }

    public function save(Bid $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Bid $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }
    
    public function MaxBidAmout()
{
    $qb = $this->createQueryBuilder('Bid');
    $qb->select('MAX(Bid.bid_amount)');
    return $qb->getQuery()->getSingleScalarResult();
}


    
public function highestBid(int $id_listing): int {
    $highestBid = 0;
    
    try {
        $queryBuilder = $this->createQueryBuilder('b')
            ->select('MAX(b.bidAmount) AS highest_bid')
            ->andWhere('b.produit = :produit_id')
            ->setParameter('produit_id', $id_listing);
        $query = $queryBuilder->getQuery();
        $result = $query->getSingleResult();
        
        if ($result && isset($result['highest_bid']) && $result['highest_bid']!=null) {
            $highestBid = (int) $result['highest_bid'];
            
        } else{ $highestBid = 0;}
    } catch (\Doctrine\ORM\NoResultException $e) {
        $highestBid = 0;
    } catch (\Doctrine\ORM\NonUniqueResultException $e) {
        $highestBid = 0;
    } catch (\Exception $e) {
        $highestBid = 0;
    }
    
    return $highestBid;
}


//    /**
//     * @return Bid[] Returns an array of Bid objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('b')
//            ->andWhere('b.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('b.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Bid
//    {
//        return $this->createQueryBuilder('b')
//            ->andWhere('b.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
